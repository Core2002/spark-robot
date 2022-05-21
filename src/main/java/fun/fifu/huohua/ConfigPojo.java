package fun.fifu.huohua;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.File;

@Data
public class ConfigPojo {
    public static final File file = new File("Config.json");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        if (!file.isFile()) {
            String s = gson.toJson(new ConfigPojo());
            IOTools.writeTextFile(s, "UTF-8", file.getAbsolutePath());
            System.out.println(s);
            System.out.println("已自动创建配置文件 Config.json ，请填写");
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
