package com.zufaralam02.sempoasip.Student.Challenge.Models;

/**
 * Created by user on 02/01/2018.
 */

public class ModelChallenge {
    private int tvJudul, tvKeterangang;
    private boolean isAvailable;

    public int getTvJudul() {
        return tvJudul;
    }

    public void setTvJudul(int tvJudul) {
        this.tvJudul = tvJudul;
    }

    public int getTvKeterangang() {
        return tvKeterangang;
    }

    public void setTvKeterangang(int tvKeterangang) {
        this.tvKeterangang = tvKeterangang;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ModelChallenge(int tvJudul, int tvKeterangang, boolean isAvailable) {
        setTvJudul(tvJudul);
        setTvKeterangang(tvKeterangang);
        setAvailable(isAvailable);
    }


}
