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

    public PrismFile getOrCreate(String path) {
        String normalized = normalize(path);
        PrismFile prismFile = files.get(normalized);
        if (prismFile != null) {
            return prismFile;
        }
        return create(normalized);
    }

    public void reload(String path) {
        get(path).reload();
    }

    public void save(String path) {
        get(path).save();
    }

    public void reloadAll() {
        for (PrismFile prismFile : files.values()) {
            prismFile.reload();
        }
    }

    public void saveAll() {
        for (PrismFile prismFile : files.values()) {
            prismFile.save();
        }
    }

    private String normalize(String path) {
        String normalized = path.replace("\\", "/").trim();
        while (normalized.startsWith("/")) {
            normalized = normalized.substring(1);
        }
        return normalized;
    }
}