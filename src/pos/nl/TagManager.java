/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.nl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */

/*
    int[] soTuLoais;
    {R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny}
[]   0  1  2  3  4  5  6   7  8  9  10  11 12 13  14
 */
public class TagManager {

    private  ArrayList<Word> wordsList;
    private ArrayList<Tag> tagList;
    private final Tag R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny, Ps, P_s;
    public String stringPosTagged;

    public TagManager() {
        wordsList = new ArrayList<>();
        tagList = new ArrayList<>();
        this.R = new Tag("R", 1);
        this.P = new Tag("P", 1);
        this.V = new Tag("V", 1);
        this.A = new Tag("A", 1);
        this.C = new Tag("C", 1);
        this.N = new Tag("N", 1);
        this.X = new Tag("X", 1);
        this.CH = new Tag("CH", 1);
        this.E = new Tag("E", 1);
        this.Np = new Tag("Np", 1);
        this.T = new Tag("T", 1);
        this.L = new Tag("L", 1);
        this.M = new Tag("M", 1);
        this.Nc = new Tag("Nc", 1);
        this.Ny = new Tag("Ny", 1);
        this.Ps = new Tag("Ps", 1);
        this.P_s = new Tag("P_s", 1);
        Collections.addAll(tagList, R, P, V, A, C, N, X, CH, E, Np, T, L, M, Nc, Ny, Ps, P_s);
        
    }

    boolean CheckWordStat(String stringData) {
        String theWord = stringData.split("/")[0];
        String tag = stringData.split("/")[1];

        if (theWord.equals("start") || theWord.equals("end")) {
            return false;
        }
        if (wordsList.isEmpty()) {
            Word newWord = new Word(theWord, 1, tag);
            wordsList.add(newWord);
        } else {
            for (Word word : wordsList) {
                if (word.getTheWord().equals(theWord)) {
                    word.AddOneWord();
                    word.TangGiaTriTuLoais(tag);
                    return true;
                }
            }
            Word newWord = new Word(theWord, 1, tag);
            wordsList.add(newWord);
        }
        return false;
    }

    public void print() {
        for (Word word : wordsList) {
            System.out.println(word.toString());
        }
        for (Tag tag : tagList) {
            System.out.println(tag.toString());
        }
    }
    private Tag TimTagWithName(String name){
        for (Tag tag : tagList) {
            if(tag.getTagName().equals(name))
                return tag;
        }
        return P;
    }
    public boolean CheckTagStat(String stringData, String preStringData) {
        //String theWord = stringData.split("/")[0];
        String curTagName = stringData.split("/")[1];
        String preTagName = "";
        if(!preStringData.equals("")){
             preTagName = preStringData.split("/")[1];
        }
        //Tag preTag = null;
        for (Tag curTag : tagList) {
            //System.out.println(curTag.getTagName());
            if (curTag.getTagName().equals(curTagName)) {
                //System.out.println("current tag: "+curTagName);
                switch (curTagName) {
                    //start
                    
                    case "Ps":
                        //System.out.println("it is -start.");
                        //curTag.TangGiaTriTuLoais("Ps");
                        //preTag = curTag;
                        curTag.AddOneTag();
                        return true;
                    
                    default:
                        //System.out.println("Cụm đang tính: " + preStringData);
                        //System.out.println("Từ đang tính': " + TimTagWithName(preTagName).getTagName());
                        curTag.AddOneTag();
                        TimTagWithName(preTagName).TangGiaTriTuLoais(curTagName);
                        return true;
                }
            }
        }
        return false;

    }
    
    public void PostTag(String textInput){
        ArrayList<Word> listInputWord = new ArrayList<>();
        String[] textSplit = textInput.split(" ");
        Word theCurrentWord;
        for (String str : textSplit) {
            Word word = new Word(str, 1, "");
            listInputWord.add(word);
        }

        for (int i = 0;i < listInputWord.size();i++ ) {
            theCurrentWord = listInputWord.get(i);
            
            for (Word wordExs : wordsList) {
                
                if(wordExs.getTheWord().equals(theCurrentWord.getTheWord())){
                    if(i == 0){
                        for (int j = 0;j < wordExs.getSoTuLoais().length;j++) {
                            double giaTriTuLoai = this.Ps.getXSTuLoaiLienSau(j);
                            double giaTriCuaTu = wordExs.getXacSuatTuLoai()[j];
                            System.out.println("giaTriTuLoai: "+ giaTriTuLoai+" - giaTriCuaTu: "+ wordExs.TimTag(j)+" "+ giaTriCuaTu);
                            theCurrentWord.setXSTuLoais(j, giaTriCuaTu * giaTriTuLoai);
                        }
                    }
                    else{
                        Word thePreviousWord = listInputWord.get(i-1);
                        for(int m = 0;m < theCurrentWord.getSoTuLoais().length;m++){
                            double[] arrTuLoai = new double[15];
                            
                            for(int n = 0;n < thePreviousWord.getSoTuLoais().length;n++){
                                double word_Tag = thePreviousWord.getXacSuatTuLoai()[n];
                                word_Tag = Math.ceil(word_Tag * 1000) / 1000;
                                double tag_tag = tagList.get(n).getXSTuLoaiLienSau(n);
                                tag_tag = Math.ceil(tag_tag * 1000) / 1000;
                                System.out.println("wordTag: "+ word_Tag + " tagTag: "+ tag_tag);
                                double res = word_Tag * tag_tag;
                                res = Math.ceil(res * 1000) / 1000;
                                arrTuLoai[n] = res;
                                System.out.println("result -> "+arrTuLoai[n]);
                            }
                            sapXepArr(arrTuLoai);
                            theCurrentWord.setXSTuLoais(m, arrTuLoai[0] * wordExs.getXacSuatTuLoai()[m]);
                        }
                    }
                }
            }
            
            
        }
        PrintResult(listInputWord);
        stringPosTagged =  GanNhan(listInputWord);
    }
    private void sapXepArr(double[] arr) {
        double temp = arr[0];
        for (int i = 0 ; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }
    private void PrintResult(ArrayList<Word> wordInputList){
        
        for (Word word : wordInputList) {
            System.out.println(word.getTheWord()+" =>");
            for(int i = 0;i < word.getXacSuatTuLoai().length;i++){
                double xs = word.getXacSuatTuLoai()[i];
                if(xs == 0.0){
                    continue;
                }
                System.out.println(word.TimTag(i) + " - " + xs);
            }
            
        }

    }
    public void ProcessString(File f) {
        //File f;
        try {
            //f = new File(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                      new FileInputStream(f), "UTF-16"));
            String line = "";
            while (br.readLine() != null) {

                line = br.readLine();
                //System.out.println(line);
                if (line == null) {
                    break;
                }
                String[] strSplit = line.split(" ");
                //Bảng trạng thái của từ

                for (String stringData : strSplit) {
                    CheckWordStat(stringData);
                }

                //Bảng trạng thái từ loại
                for (int i = 0; i < strSplit.length; i++) {
                    if(i == 0)
                        CheckTagStat(strSplit[0], "");
                    else
                        CheckTagStat(strSplit[i], strSplit[i-1]);
                }
                

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TagManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TagManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String GanNhan(ArrayList<Word> listInput) {
        String res = "";
        for (Word word : listInput) {
            double xsLonNhat = word.getXacSuatTuLoai()[0];
            int viTri = 0;
            for(int i = 0;i < word.getXacSuatTuLoai().length;i++){
                if(word.getXacSuatTuLoai()[i] > xsLonNhat){
                    xsLonNhat = word.getXacSuatTuLoai()[i];
                    viTri = i;
                }
            }
            res = res + word.getTheWord() + "/" + word.TimTag(viTri ) + " ";
        }
        return res;
    }



}
