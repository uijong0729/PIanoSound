package Utility;

public class Note {

    private int index;
    private Long milisecond;
    private int note;
    private Long idle;
    private String kr;


    public Note(Long milisecond, int note, Long idle) {
        this.milisecond = milisecond;
        this.note = note;
        this.kr = transfer(note);
        this.idle = idle;
    }


    public Note(int index, Long milisecond, int note, Long idle) {
        this.index = index;
        this.milisecond = milisecond;
        this.note = note;
        this.kr = transfer(note);
        this.idle = idle;
    }

    public String transfer(int note){

        switch (note)
        {
            case 0: return "도";
            case 1: return "레";
            case 2: return "미";
            case 3: return "파";
            case 4: return "솔";
            case 5: return "라";
            case 6: return "시";
            case 7: return "도!";
            case 10: return "도#";
            case 11: return "레#";
            case 13: return "파#";
            case 14: return "솔#";
            case 15: return "라#";
            default: return "empty";
        }

    }





    public String getKr() {
        return kr;
    }
    public Long getMilisecond() {
        return milisecond;
    }
    public int getNote() {
        return note;
    }
    public Long getIdle(){return idle;}


    @Override
    public String toString() {
        return "Note{index=" + index +
                ",milisecond=" + milisecond +
                ", note=" + note +
                ", kr=" + kr +
                ", idle = "+idle+"}";
    }
}
