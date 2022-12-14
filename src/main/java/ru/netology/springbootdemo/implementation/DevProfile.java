package ru.netology.springbootdemo.implementation;

public class DevProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profil is dev";
    }
}
