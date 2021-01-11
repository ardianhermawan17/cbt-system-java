package CBTSchoolSystem;

import CBTSchoolSystem.TeacherSoalView.*;

public class Comunicator {
    private HomeView homeView;
    private SoalView soalView;
    private SoalListTeacherView soalListTeacherView;
    private AddSoalView addSoalView;
    private EditSoalView editSoalView;
    private StartCBT startCBT;
    private HasilCBT hasilCBT;
    //Global variable
    private int indexSoal = 0;
    private int index = 0;
    private double score = 0;


    public Comunicator(){
        Controller controller= new Controller();
        this.homeView  = new HomeView("Home View",controller, this);
        this.soalView = new SoalView("Soal View", controller, this);
        this.soalListTeacherView = new SoalListTeacherView("Soal List Teacher View", controller, this);
        this.addSoalView = new AddSoalView("Add Soal View", controller, this);
        this.editSoalView = new EditSoalView("Edit Soal View", controller, this);
        this.startCBT = new StartCBT("Start CBT", this);
        this.hasilCBT = new HasilCBT("Hasil CBT", this);
        homeView.setVisible(true);
    }

    //HomeView
    public void openHomeView(){
        homeView.setVisible(true);
    }

    public void closeHomeView() {
        homeView.setVisible(false);
    }

    //SoalView
    public void openSoalView() {
        soalView.renderSoal(indexSoal);
        soalView.setVisible(true);
    }

    public void closeSoalView() {
        soalView.dispose();
    }

    //ListTeacherView
    public void openSoalListTeacherView() {
        soalListTeacherView.setVisible(true);
        soalListTeacherView.renderSoal();
    }

    public void closeSoalListTeacherView(){
        soalListTeacherView.setVisible(false);
    }

    //AddSoalView
    public void openAddSoalView() {
        addSoalView.setVisible(true);
    }

    public void closeAddSoalView() {
        addSoalView.setVisible(false);
    }

    //EditSoalView
    public void openEditSoalView() {
        editSoalView.setVisible(true);
    }

    public void updateEditSoalView(){
        editSoalView.renderSoal();
    }

    public void closeEditSoalView() {
        editSoalView.setVisible(false);
    }

    //Start CBT
    public void openStartCBT() {
        startCBT.setVisible(true);
    }

    public void closeStartCBT() {
        startCBT.dispose();
    }

    //Hasil CBT
    public void openHasilCBT() {
        hasilCBT.setVisible(true);
        hasilCBT.setHasil();
    }

    public void closeHasilCBT(){
        hasilCBT.dispose();
    }

    //SETTER GETTER
    public int getIndexSoal() {
        return indexSoal;
    }

    public void setIndexSoal(int indexSoal) {
        this.indexSoal = indexSoal;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
