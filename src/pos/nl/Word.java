/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.nl;

/**
 *
 * @author hp
 */
public class Word {
    
    /*
    int[] soTuLoais;
    {R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny}
[]   0  1  2  3  4  5  6   7  8  9   10 11 12 13  14
    */
    
    public enum tuLoai {R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny};//, Ps, P_s};
    private final String theWord;
    private int theCount;
    private double probability;
    //private ArrayList<Integer> soTuLoais;
    private int[] soTuLoais;
    private double[] xacSuatTuLoai;
    public Word(String word, int count, String tuLoai)
    {
        //soTuLoais = new ArrayList<>();
        
        theWord = word;
        theCount = count;
        probability = 0.01;
        KhoiTaoMangTuLoai();
        TangGiaTriTuLoais(tuLoai);        
    }
    private void KhoiTaoMangTuLoai(){
        soTuLoais = new int[15];
        xacSuatTuLoai = new double[15];
        for (int i = 0;i < 15;i++) {
            soTuLoais[i] = 0;
            xacSuatTuLoai[i] = 0;
        }
        //for (int i = 0;i< 13;i++) {
            //soTuLoais.add(0);
        //}
        //{R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny}
        // 0  0  0  0  0  0  0  0   0  0   0  0  0  0   0
    }
    public void setCount(int count)
    {
        theCount = count;
    }

    public void setTheCount(int theCount) {
        this.theCount = theCount;
    }

    public void setGiaTriTuLoais(int viTri, int giaTri) {
        //this.soTuLoais.set(viTri, giaTri);
        this.soTuLoais[viTri] = giaTri;
    }
    

    public void setXSTuLoais(int viTri, double giaTri) {
        //this.soTuLoais.set(viTri, giaTri);
        this.xacSuatTuLoai[viTri] = giaTri;
    }

    public void setGiaTriTuLoais(String tagName, int giaTri) {
        //this.soTuLoais.set(TimTag(tagName), giaTri);
        this.soTuLoais[TimTag(tagName)] = giaTri;
    }
    
    public void TangGiaTriTuLoais(String tagName) {
        int vitri = TimTag(tagName);
        this.soTuLoais[vitri]++;
        xacSuatTuLoai[vitri] = TinhXacSuatTuLoai(tagName);        
        //this.soTuLoais.set(vitri, this.soTuLoais.get(vitri) + 1);// = this.soTuLoais[vitri] + 1;
    }
    
    public int TimTag(String newtag){
        switch(newtag){
            
            case "R": return 0;
            case "P": return 1;
            case "V": return 2;
            case "A": return 3;
            case "C": return 4;
            case "N": return 5;
            case "X": return 6;
            case "CH": return 7;
            case "E": return 8;
            case "Np": return 9;
            case "T": return 10;
            case "L": return 11;
            case "M": return 12;
            case "Nc": return 13;
            case "Ny": return 14;
            case "Ps": return 15;
            case "P_s":return 16;
            default: System.out.println(newtag);
                    return 5;
        }
    }
    public String TimTag(int tagCanTim){
        switch(tagCanTim){
            
            case 0 : return "R";
            case 1 : return "P";
            case 2 : return "V";
            case 3 : return "A";
            case 4 : return "C";
            case 5 : return "N";
            case 6 : return "X";
            case 7 : return "CH";
            case 8 : return "E";
            case 9 : return "Np";
            case 10 : return "T";
            case 11 : return "L";
            case 12 : return "M";
            case 13 : return "Nc";
            case 14 : return "Ny";
            case 15 : return "Ps";
            case 16 :return "P_s";
            default: System.out.println(tagCanTim);
                    return "N";
        }
    }
    public double TinhXacSuatTuLoai(String tagName){
        int vitri = TimTag(tagName);
        double xs = Double.valueOf(soTuLoais[vitri]) / Double.valueOf(this.theCount);
        //System.out.println("tinh xs: "+ this.theWord + " "+ tagName+ " "+soTuLoais[vitri] + " - "+ this.theCount);
        
        xs = Math.ceil(xs * 10000) / 10000;
        //System.out.println("res => " + xs);
        return xs;
    }
    public void AddOneWord(){
        
        this.theCount++;
    }
    
    public String getTheWord()
    {
        return theWord;
    }
 
    public int getTheCount()
    {
        return theCount;
    }

    public void setProbability(double value){
        probability = value;
    }

    public double getProbability() {
        return probability;
    }

    public int[] getSoTuLoais() {
        return soTuLoais;
    }

    public double[] getXacSuatTuLoai() {
        return xacSuatTuLoai;
    }
    
    @Override
    public String toString() {
        String tuLoaiList = "";
        String xStuLoaiList = "";
        for (int soTuLoai : soTuLoais) {
            tuLoaiList += soTuLoai + " ";
        }
        for (double xsTuLoai : xacSuatTuLoai) {
            xStuLoaiList += xsTuLoai + " ";
        }
        return this.theWord + " || Count: " + theCount +" |"+ tuLoaiList +" |"+ xStuLoaiList; //+ " || Probability: "+this.probability;
    }
    


}
