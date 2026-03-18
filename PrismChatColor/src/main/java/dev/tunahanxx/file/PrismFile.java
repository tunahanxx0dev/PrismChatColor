package dev.tunahanxx.file;

import dev.tunahanxx.PrismChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class PrismFile {

    private final String path;
    private final File file;
    private YamlConfiguration configuration;

    PrismFile(String path) {
        this.path = normalize(path);
        this.file = new File(PrismChatColor.getInstance().getDataFolder(), this.path);
    }

    public PrismFile build() {
        createParentDirectories();
        copyFromResourcesIfAbsent();
        createEmptyFileIfAbsent();
        this.configuration = YamlConfiguration.loadConfiguration(file);
        return this;
    }

    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        if (configuration == null) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path);
        }
        try {
            configuration.save(file);
        } catch (IOException exception) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path, exception);
        }
    }

    public YamlConfiguration config() {
        if (configuration == null) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path);
        }
        return configuration;
    }

    public File file() {
        return file;
    }

    public String path() {
        return path;
    }

    private void createParentDirectories() {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    private void copyFromResourcesIfAbsent() {
        if (file.exists()) {
            return;
        }
        try (InputStream inputStream = PrismChatColor.getInstance().getResource(path)) {
            if (inputStream == null) {
                return;
            }
            Files.copy(inputStream, file.toPath());
        } catch (IOException exception) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path, exception);
        }
    }

    private void createEmptyFileIfAbsent() {
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException exception) {
            throw new IllegalStateException("[PrismChatColor] Exception: " + path, exception);
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