package io.github.siaust.code_sharer.Repository;

import io.github.siaust.code_sharer.Model.Snippet;

public class TempRepo {

    private static Snippet snippet;

    public static Snippet getSnippet() {
        return snippet;
    }

    public static void setSnippet(Snippet codeSnippet) {
        snippet = codeSnippet;
    }
}
