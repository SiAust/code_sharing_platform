package io.github.siaust.code_sharer.Service;

import io.github.siaust.code_sharer.Model.Snippet;
import io.github.siaust.code_sharer.Repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SnippetService {

    @Autowired
    SnippetRepository snippetRepository;

    public Snippet getSnippet(UUID uuid) {
        Snippet snippet = snippetRepository.findById(uuid).orElse(null);
        /* If secret snippet, update it, otherwise return snippet */
        return snippet == null ? null : snippet.isSecret() ? updateSnippet(snippet) : snippet;
    }

    public List<Snippet> getLatest() {
        return snippetRepository.findTop10BySecretOrderByDateDesc(false);
    }

    public Map<String, String> saveSnippet(Snippet snippet) {
        /* Checks if there has been a value assigned to time or views and sets boolean secret field
        to true if so */
        snippet.setSecret(snippet.getTime() > 0 || snippet.getViews() > 0);
        return Map.of("id", String.valueOf(snippetRepository.save(snippet).getId().toString()));
    }

    /** Updates the Snippet if it is secret. This decrements the views field
     * and subtracts the time since creation to the time field.
     * If the snippet is expired in views or time it will be deleted from the DB, otherwise
     * the DB entry will be updated with the new values.
     * @param snippet the Snippet to be updated */
    private Snippet updateSnippet(Snippet snippet) {
        LocalDateTime now = LocalDateTime.now();

        /* Decrement views by one */
        snippet.setViews(snippet.getViews() - 1);
        /* Subtract seconds from time restriction value */
        if (snippet.getLastTimeCheck() == null) {
            snippet.setTime(snippet.getTime()
            - (now.toEpochSecond(ZoneOffset.UTC)
            - snippet.getLocalDateTime().toEpochSecond(ZoneOffset.UTC)));
        } else {
            snippet.setTime(snippet.getTime()
            - (now.toEpochSecond(ZoneOffset.UTC)
            - snippet.getLastTimeCheck().toEpochSecond(ZoneOffset.UTC)));
        }
        snippet.setLastTimeCheck(now);

        /* If snippet is secret and time or views expired, delete from DB */
        if (snippet.getTime() < 1 || snippet.getViews() < 0) {
            snippetRepository.delete(snippet);
            return null;
        }
        return snippetRepository.save(snippet);
    }

}
