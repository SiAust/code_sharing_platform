package io.github.siaust.code_sharer.Repository;

import io.github.siaust.code_sharer.Model.Snippet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    Optional<Snippet> findById(UUID uuid);
    /* return top 10 snippets where secret true/false order by date desc */
    List<Snippet> findTop10BySecretOrderByDateDesc(boolean secret);
}
