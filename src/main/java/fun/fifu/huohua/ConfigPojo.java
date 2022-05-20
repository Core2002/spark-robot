package fun.fifu.huohua;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Data
public class ConfigPojo {
    public static final File file = new File("Config.json");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        if (!file.isFile()) {
            try {
                new FileWriter(file, StandardCharsets.UTF_8).write(gson.toJson(new ConfigPojo()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ConfigPojo readConfig() {
        ConfigPojo configPojo = gson.fromJson(IOTools.readTextFile(file.getAbsolutePath(), "UTF-8"), ConfigPojo.class);
        this.botId = configPojo.getBotId();
        this.botPassword = configPojo.getBotPassword();
        return this;
    }

    public void saveData() {
        IOTools.writeTextFile(gson.toJson(this), "UTF-8", file.getAbsolutePath());
    }


    Long ownerId = 3513353936L;
    Long botId = 0L;
    String botPassword = "请输入密码";
}
