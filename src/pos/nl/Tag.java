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
    /*
    int[] soTuLoais;
    {R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny, Ps, P_s}
[]   0  1  2  3  4  5  6   7  8  9   10 11 12 13  14  15  16
    */
public class Tag {
    private String tagName;
    private int[] cacTuLoaiLienSau;
    private double[] xacSuatTuLoai;
    private int theCount;

    public Tag(String tagName, int theCount) {
        this.tagName = tagName;
        this.theCount = theCount;
        KhoiTaoMangTuLoai();
    }
    private void KhoiTaoMangTuLoai(){
        cacTuLoaiLienSau = new int[17];
        xacSuatTuLoai = new double[17];
        for (int i = 0;i < 17;i++) {
            cacTuLoaiLienSau[i] = 0;
            xacSuatTuLoai[i] = 0.001;
        }
        //for (int i = 0;i< 13;i++) {
            //soTuLoais.add(0);
        //}
        //{R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny}
        // 0  0  0  0  0  0  0  0   0  0   0  0  0  0   0
    }
    
    public void TangGiaTriTuLoais(String tagName) {
        System.out.println("TangGiaTriTuLoais của "+this.tagName +" cho "+tagName);
        int vitri = TimTag(tagName);
        this.cacTuLoaiLienSau[vitri]++;
        System.out.println("cacTuLoaiLienSau - "+cacTuLoaiLienSau[vitri]);
        xacSuatTuLoai[vitri] = TinhXacSuatTuLoai(tagName);        
        //this.soTuLoais.set(vitri, this.soTuLoais.get(vitri) + 1);// = this.soTuLoais[vitri] + 1;
    }
    public double TinhXacSuatTuLoai(String tagName){
        //System.out.println("Tu lien sau"+);
        int vitri = TimTag(tagName);
        double xs = Double.valueOf(cacTuLoaiLienSau[vitri]) / Double.valueOf(this.theCount);
        System.out.println("Tính xs của: "+ tagName+ " " + cacTuLoaiLienSau[vitri] + " - count: "+ this.theCount);
        xs = Math.ceil(xs * 10000) / 10000;
        return xs;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void AddOneTag(){ 
        this.theCount++;
    }
    
    public int getTheCount() {
        return theCount;
    }

    public void setTheCount(int theCount) {
        this.theCount = theCount;
    }
    
    public int getTuLoaiLienSau(int viTri){
        return this.cacTuLoaiLienSau[viTri];
    }
    public double getXSTuLoaiLienSau(int viTri){
        return this.xacSuatTuLoai[viTri];
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
            case "P_s": return 16;
            default: System.out.println(newtag);
                    return 5;
        }
    }

    @Override
    public String toString() {
        String tuLoaiList = "";
        String xStuLoaiList = "";
        for (int soTuLoai : cacTuLoaiLienSau) {
            tuLoaiList += soTuLoai + " ";
        }
        for (double xsTuLoai : xacSuatTuLoai) {
            xStuLoaiList += xsTuLoai + " ";
        }
        return this.tagName + " || Count: " + theCount +" | "+ tuLoaiList +" | "+ xStuLoaiList;
    }

    
}
