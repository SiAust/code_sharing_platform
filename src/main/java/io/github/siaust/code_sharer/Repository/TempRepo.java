package io.github.siaust.code_sharer.Repository;

import org.springframework.stereotype.Service;
import io.github.siaust.code_sharer.Model.Snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TempRepo {

    private List<Snippet> snippetList = new ArrayList<>();

    public Optional<Snippet> getSnippet(int index) {
        try {
            return Optional.of(snippetList.get(index));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public int getID(Snippet snippet) {
        return snippetList.indexOf(snippet);
    }

    public List<Snippet> getAllSnippets() {
        return snippetList;
    }

    public void addSnippet(Snippet codeSnippet) {
        snippetList.add(codeSnippet);
    }
}
