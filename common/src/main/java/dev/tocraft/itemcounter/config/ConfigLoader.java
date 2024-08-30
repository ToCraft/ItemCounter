package dev.tocraft.itemcounter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import dev.tocraft.itemcounter.platform.Platform;

import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ConfigLoader {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();

    public static ItemCounterConfig register() {
        ItemCounterConfig instance = new ItemCounterConfig();

        if (!getConfigPath().toFile().exists()) {
            saveConfig(instance);
        } else {
            try {
                //List<String> lines = Files.readAllLines(getConfigPath());
                //lines.removeIf(s -> s.trim().startsWith("//"));
                //StringBuilder sb = new StringBuilder();
                //lines.forEach(sb::append);
                String json = Files.readString(getConfigPath());
                ItemCounterConfig object = GSON.fromJson(json, ItemCounterConfig.class);
                saveConfig(object);
                return object;
            } catch (IOException | IllegalStateException e) {
                LogUtils.getLogger().error("Couldn't read ItemCounter Config!", e);
                saveConfig(instance);
            }
        }

        return instance;
    }

    public static void saveConfig(ItemCounterConfig instance) {
        String json = GSON.toJson(instance);
        List<String> lines = new ArrayList<>(json.lines().toList());
        Map<Integer, String> insertions = new TreeMap<>();
        Map<Field, String> fieldToComments = new HashMap<>();
        for (Field field : ItemCounterConfig.class.getDeclaredFields()) {
            instantiateFieldComments(field, fieldToComments);
        }

        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            String indentation = getIndentation(s);

            for (Map.Entry<Field, String> entry : fieldToComments.entrySet()) {
                String comment = entry.getValue();
                if (s.trim().startsWith(String.format("\"%s\"", entry.getKey().getName()))) {
                    if (comment.contains("\n")) {
                        comment = indentation + "//" + String.join(String.format("\n%s//", indentation), comment.split("\n"));
                    } else {
                        comment = String.format("%s//%s", indentation, comment);
                    }
                    insertions.put(i + insertions.size(), comment);
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        insertions.forEach(lines::add);
        lines.forEach(s -> res.append(String.format("%s%n", s)));

        try {
            Path configPath = getConfigPath();
            //noinspection ResultOfMethodCallIgnored
            configPath.toFile().getParentFile().mkdirs();
            Files.writeString(configPath, res.toString());
        } catch (IOException e) {
            LogUtils.getLogger().error("Couldn't write ItemCounter Config!", e);
        }
    }

    private static Path getConfigPath() {
        return Platform.getConfigPath().resolve("itemcounter.json5");
    }

    private static String getIndentation(String s) {
        return " ".repeat(s.length() - s.trim().length());
    }

    private static void instantiateFieldComments(Field field, Map<Field, String> fieldToComment) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (annotation instanceof Comment comment) {
                fieldToComment.put(field, comment.value());
                break;
            }
        }
    }
}
