package com.zufaralam02.sempoasip.Parent.Wallet.Models;

/**
 * Created by user on 19/01/2018.
 */

public class ModelWallet {
    private int walletName;
    private int walletCoin;
    private boolean isPeding;

    public int getWalletName() {
        return walletName;
    }

    public void setWalletName(int walletName) {
        this.walletName = walletName;
    }

    public int getWalletCoin() {
        return walletCoin;
    }

    public void setWalletCoin(int walletCoin) {
        this.walletCoin = walletCoin;
    }

    public boolean isPeding() {
        return isPeding;
    }

    public void setPeding(boolean peding) {
        isPeding = peding;
    }

    public ModelWallet(int walletName, int walletCoin, boolean isPeding) {
        setWalletName(walletName);
        setWalletCoin(walletCoin);
        setPeding(isPeding);
    }

}
