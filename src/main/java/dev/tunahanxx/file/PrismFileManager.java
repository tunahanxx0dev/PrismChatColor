package dev.tunahanxx.file;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PrismFileManager {

    private final Map<String, PrismFile> files = new ConcurrentHashMap<>();

    public PrismFile create(String path) {
        String normalized = normalize(path);
        PrismFile prismFile = new PrismFile(normalized).build();
        files.put(normalized, prismFile);
        return prismFile;
    }

    public PrismFile get(String path) {
        PrismFile prismFile = files.get(normalize(path));
        if (prismFile == null) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path);
        }
        return prismFile;
    }

    private String normalize(String path) {
        String normalized = path.replace("\\", "/").trim();
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        return normalized;
    }
}