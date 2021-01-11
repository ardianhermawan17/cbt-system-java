package CBTSchoolSystem.Database;

import CBTSchoolSystem.Data.SoalDataModel;
import CBTSchoolSystem.Data.UserDataModel;

import java.util.ArrayList;

public class Database {
    private ArrayList<UserDataModel> User  = new ArrayList<>();
    private ArrayList<SoalDataModel> Soal = new ArrayList<>();

    public ArrayList<UserDataModel> getUser() {
        return User;
    }

    public ArrayList<SoalDataModel> getSoal() {
        return Soal;
    }
}
