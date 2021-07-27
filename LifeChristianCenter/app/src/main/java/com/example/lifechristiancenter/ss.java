// Your package name can be different depending 
// on your project name 
package com.example.lifechristiancenter;

public class ss
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String name;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String lastname;

    // Variable to store data corresponding
    // to age keyword in database
    private String dob;
    private String image;
    private String image1;
    // Mandatory empty constructor
    // for use of FirebaseUI
    public ss() {}

    // Getter and setter method
    public String getname()
    {
        return name;
    }
    public void setname(String name)
    {
        this.name = name;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public String getdob()
    {
        return dob;
    }
    public void setdob(String dob)
    {
        this.dob = dob;
    }
    public String getimage()
    {
        return image;
    }
    public void setimage(String image)
    {
        this.image = image;
    }
    public String getimage1()
    {
        return image1;
    }
    public void setimage1(String image1)
    {
        this.image1 = image1;
    }
}
