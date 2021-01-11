package CBTSchoolSystem.Data;

public class SoalDataModel {
    private String soal;
    private String[] jawaban;
    private String jawabanBenar;

    public SoalDataModel(String soal, String[] jawaban, String jawabanBenar) {
        this.soal = soal;
        this.jawaban = jawaban;
        this.jawabanBenar = jawabanBenar;
    }

    public String getSoal() {
        return soal;
    }

    public String[] getJawaban() {
        return jawaban;
    }

    public String getJawabanBenar() {
        return jawabanBenar;
    }
}