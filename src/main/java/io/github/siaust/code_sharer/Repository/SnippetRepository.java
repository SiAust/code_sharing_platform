package io.github.siaust.code_sharer.Repository;

import io.github.siaust.code_sharer.Model.Snippet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    List<Snippet> findTop10ByOrderByIdDesc();
}
