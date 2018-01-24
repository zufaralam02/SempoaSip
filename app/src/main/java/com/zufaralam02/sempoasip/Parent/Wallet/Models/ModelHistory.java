package com.zufaralam02.sempoasip.Parent.Wallet.Models;

/**
 * Created by user on 19/01/2018.
 */

public class ModelHistory {
    private int historyName;
    private int historyTime;
    private int historyCoin;
    private boolean isPending;

    public int getHistoryName() {
        return historyName;
    }

    public void setHistoryName(int historyName) {
        this.historyName = historyName;
    }

    public int getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(int historyTime) {
        this.historyTime = historyTime;
    }

    public int getHistoryCoin() {
        return historyCoin;
    }

    public void setHistoryCoin(int historyCoin) {
        this.historyCoin = historyCoin;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public ModelHistory(int historyName, int historyTime, int historyCoin, boolean isPending) {
        setHistoryName(historyName);
        setHistoryTime(historyTime);
        setHistoryCoin(historyCoin);
        setPending(isPending);
    }
}
