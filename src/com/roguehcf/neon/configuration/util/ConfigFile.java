package com.roguehcf.neon.configuration.util;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFile extends YamlConfiguration {
    private final String fileName;
    private final JavaPlugin plugin;

    public ConfigFile(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }
    
    public ConfigFile(JavaPlugin plugin, String fileName, boolean suffix) {
    	this(plugin, fileName, (suffix) ? ".yml" : "");
    }
    
    public ConfigFile(JavaPlugin plugin, String fileName, String fileExtension) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.createFile();
    }

    public String getFileName() {
        return this.fileName;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    private void createFile() {
        File folder = this.plugin.getDataFolder();
        try {
            File file = new File(folder, this.fileName);
            if (!file.exists()) {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.save(file);
                }
            } else {
                this.load(file);
                this.save(file);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void save() {
        File folder = this.plugin.getDataFolder();
        try {
            this.save(new File(folder, this.fileName));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigFile)) {
            return false;
        }
        ConfigFile config = (ConfigFile) o;
        if (this.fileName != null ? !this.fileName.equals(config.fileName) : config.fileName != null) {
            return false;
        }
        if (this.plugin != null) {
            return this.plugin.equals(config.plugin);
        }
        return config.plugin == null;
    }

    public int hashCode() {
        int result = this.fileName != null ? this.fileName.hashCode() : 0;
        result = 31 * result + (this.plugin != null ? this.plugin.hashCode() : 0);
        return result;
    }
}


