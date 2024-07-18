package uz.pdp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WriterAndLoader<M> {
    private final Gson gson;
    private final Path path;

    public WriterAndLoader(@NonNull String path) {
        this.path = Path.of(path);
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void fileWrite(@NonNull List<M> mList) {
        String json = gson.toJson(mList);

        try {
            Files.writeString(path, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<M> fileLoader(@NonNull Class<M> mClass) {
        if (Files.exists(path)) {
            String json = null;
            try {
                json = Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Type type = TypeToken.getParameterized(List.class, mClass).getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }
}
