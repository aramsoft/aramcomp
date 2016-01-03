package aramframework.com.cmm.service;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="people")
public class People {
	
	 private List<Person> people;
	   
	 protected People() {}   // Keep JAXB happy
    
	 public People(List<Person> people)
	 {
	    this.people = people;
	 }
   
    @XmlElement(name="person")
    public List<Person> getPeople() 
    {
    	return people;
    }

}