package CBTSchoolSystem;

import CBTSchoolSystem.Data.SoalDataModel;
import CBTSchoolSystem.Data.UserDataModel;
import CBTSchoolSystem.Database.Database;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    private Database database = new Database();

    public Controller(){
        database.getSoal().add(new SoalDataModel("Aku", new String[]{"koko", "mimis","lolo"},"koko"));
        database.getSoal().add(new SoalDataModel("Aku2", new String[]{"koko", "romn","lolo"},"lolo"));
        database.getSoal().add(new SoalDataModel("dummy3", new String[]{"nina", "dio","lala"},"nina"));
    }

    public boolean addSoal(String soal, String[] jawaban, String jawabanBenar) {
        try{
            database.getSoal().add(new SoalDataModel(soal, jawaban, jawabanBenar));
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateSoal(int index, String soal, String[] jawaban, String jawabanBenar){
        try{
            database.getSoal().set(index, new SoalDataModel(soal, jawaban, jawabanBenar));
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteSoal(int index){
        try{
            database.getSoal().remove(index);
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<SoalDataModel> getSoalList(){
        return database.getSoal();
    }

    public boolean addUser(String username, String password, String role){
        try{
            //checking username is exist
            boolean validate = false;
            for(int j = 0; j < database.getUser().size();j++){
                if(database.getUser().get(j).getUsername().equals(username)){
                    validate = true;
                }
            }
            //add user
            if (!validate){
                database.getUser().add(new UserDataModel(username,password,role));
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"User already exist, use another name!");
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public String loginUser(String username, String password){
        for(int i = 0; i<database.getUser().size(); i++){
            Boolean usernameCheck = database.getUser().get(i).getUsername().equals(username);
            Boolean passwordCheck = database.getUser().get(i).getPassword().equals(password);

            //Return role
            if(usernameCheck && passwordCheck){
                return database.getUser().get(i).getRole();
            }
        }

        return "";
    }
}
