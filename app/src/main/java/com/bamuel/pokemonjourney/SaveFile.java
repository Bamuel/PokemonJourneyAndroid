package com.bamuel.pokemonjourney;

public class SaveFile {
    private int _id;
    private String _username;
    private String _gender;
    private int _steps;

    public SaveFile(String username, String gender, int steps){
        this._username = username;
        this._gender = gender;
        this._steps = steps;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public int get_steps() {
        return _steps;
    }

    public void set_steps(int _steps) {
        this._steps = _steps;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
