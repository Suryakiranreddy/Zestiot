package scheduler;
import lombok.Data;

@Data
public class Transitvisavia {
    int Id;
    String FromIndiaToCountry;
    String ViaCountry;
    String DisplayMessage;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFromIndiaToCountry() {
		return FromIndiaToCountry;
	}
	public void setFromIndiaToCountry(String fromIndiaToCountry) {
		FromIndiaToCountry = fromIndiaToCountry;
	}
	public String getViaCountry() {
		return ViaCountry;
	}
	public void setViaCountry(String viaCountry) {
		ViaCountry = viaCountry;
	}
	public String getDisplayMessage() {
		return DisplayMessage;
	}
	public void setDisplayMessage(String displayMessage) {
		DisplayMessage = displayMessage;
	}
    
    
}
