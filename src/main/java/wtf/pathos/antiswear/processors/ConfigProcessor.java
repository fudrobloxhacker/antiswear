package wtf.pathos.antiswear.processors;

import org.bukkit.configuration.file.FileConfiguration;
import wtf.pathos.antiswear.annotations.ConfigValue;

import java.lang.reflect.Field;

public class ConfigProcessor {
    public static void processConfig(Object instance, FileConfiguration config) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigValue.class)) {
                ConfigValue annotation = field.getAnnotation(ConfigValue.class);
                String path = annotation.path();
                String defaultValue = annotation.defaultValue();

                try {
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(instance, config.getString(path, defaultValue));
                    } else if (field.getType() == int.class) {
                        field.setInt(instance, config.getInt(path, Integer.parseInt(defaultValue)));
                    } else if (field.getType() == boolean.class) {
                        field.setBoolean(instance, config.getBoolean(path, Boolean.parseBoolean(defaultValue)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}