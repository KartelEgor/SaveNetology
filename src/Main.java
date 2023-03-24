import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress character1 = new GameProgress(100, 3, 10, 120);
        GameProgress character2 = new GameProgress(80, 5, 15, 210);
        GameProgress character3 = new GameProgress(50, 1, 8, 75);

//------------------------------------------Сохранение-----------------------------------------

        saveFile("C:/Users/Ksesh/Desktop/Games/savegames/save1", character1);
        saveFile("C:/Users/Ksesh/Desktop/Games/savegames/save2", character2);
        saveFile("C:/Users/Ksesh/Desktop/Games/savegames/save3", character3);

//------------------------------------------Создание списка с файлами-------------------------

        List<String> files = new ArrayList<>(List.of("C:/Users/Ksesh/Desktop/Games/savegames/save1",
                "C:/Users/Ksesh/Desktop/Games/savegames/save2",
                "C:/Users/Ksesh/Desktop/Games/savegames/save3"));

//------------------------------------------Архивация----------------------------------------

        zipFiles("C:/Users/Ksesh/Desktop/Games/savegames/out.zip", files);

//-------------------------------------------Удаление-----------------------------------------

        deleteAllFiles(files);
    }

    public static void saveFile(String path, GameProgress character) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(character);
            fos.close();
            oos.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> files) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath));

            for (String filePath : files) {
                File file = new File(filePath);

                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);

                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();

                zout.write(buffer);
                zout.closeEntry();
            }
            zout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteAllFiles(List<String> files) {
        for (String filePath : files) {
            try {
                new File(filePath).delete();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}