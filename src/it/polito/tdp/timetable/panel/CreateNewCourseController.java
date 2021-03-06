/**
 * Sample Skeleton for 'CreateNewCourse.fxml' Controller Class
 */

package it.polito.tdp.timetable.panel;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.timetable.model.Model;
import it.polito.tdp.timetable.model.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateNewCourseController {
	
	private Model model;
	private Map<String, Integer[]> mapHSubject;
	private int numHoursAssigned;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="txtGrade"
    private TextField txtGrade; // Value injected by FXMLLoader

    @FXML // fx:id="listSubjectChecked"
    private ListView<Subject> listSubjectChecked; // Value injected by FXMLLoader

    @FXML // fx:id="txtCourseSub"
    private TextField txtCourseSub; // Value injected by FXMLLoader

    @FXML // fx:id="txtHoursDisponible"
    private TextField txtHoursDisponible; // Value injected by FXMLLoader

    @FXML // fx:id="txtHoursAssigned"
    private TextField txtHoursAssigned; // Value injected by FXMLLoader

    @FXML // fx:id="txtIdSub"
    private TextField txtIdSub; // Value injected by FXMLLoader

    @FXML // fx:id="txtNameSub"
    private TextField txtNameSub; // Value injected by FXMLLoader

    @FXML // fx:id="txtHoursSub"
    private TextField txtHoursSub; // Value injected by FXMLLoader

    @FXML // fx:id="checkLab"
    private CheckBox checkLab; // Value injected by FXMLLoader

    @FXML // fx:id="vbxLab"
    private VBox vbxLab; // Value injected by FXMLLoader

    @FXML // fx:id="txtHoursLab"
    private TextField txtHoursLab; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLabSub"
    private ComboBox<String> cmbLabSub; // Value injected by FXMLLoader

    @FXML // fx:id="hbxAllertSub"
    private HBox hbxAllertSub; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddNewCourse"
    private Button btnAddNewCourse; // Value injected by FXMLLoader

    @FXML
    void addNewSub(ActionEvent event) {
    
    model.addNewCourse(txtCourseSub.getText().toUpperCase(),
    			Integer.valueOf(txtGrade.getText()),
    			mapHSubject);	
   	
    	Launcher.openTabCourse();
    }
    
    @FXML
    void returnBack(ActionEvent event) {
    	Launcher.openTabCourse();
    }

    @FXML
    void doVisibleLab(ActionEvent event) {
    	if(checkLab.isSelected()) {
    		vbxLab.setDisable(false);
    		txtHoursLab.clear();
    		cmbLabSub.getSelectionModel().clearSelection();
    	} else {
    		vbxLab.setDisable(true);
    		if(mapHSubject.containsKey(txtIdSub.getText()))
    			mapHSubject.put(txtIdSub.getText(), new Integer[] {Integer.valueOf(txtHoursSub.getText()), 0, 0});
    	}
    }
    
    @FXML
    void updateMap(KeyEvent event) {
    	if(txtIdSub.getText().isEmpty() || txtHoursSub.getText().isEmpty())
    		return;
    	
    	if(mapHSubject.containsKey(txtIdSub.getText()))
    		numHoursAssigned -= mapHSubject.get(txtIdSub.getText())[0];
    		
    	numHoursAssigned += Integer.valueOf(txtHoursSub.getText());
    	txtHoursAssigned.setText(String.valueOf(numHoursAssigned));    	
    	
    	mapHSubject.put(txtIdSub.getText(), new Integer[] {Integer.valueOf(txtHoursSub.getText()), 0, 0});
    	
    	if(numHoursAssigned == model.getHoursWeekSchool()  && mapHSubject.size() == listSubjectChecked.getItems().size())
    		btnAddNewCourse.setDisable(false);
    	else 
    		btnAddNewCourse.setDisable(true);
    	
    	if(numHoursAssigned > model.getHoursWeekSchool())
    		listSubjectChecked.setDisable(true);
    	else
    		listSubjectChecked.setDisable(false);
    	
    }
    
    @FXML
    void updateMapLab(ActionEvent event) {
    	if(txtIdSub.getText().isEmpty() || txtHoursSub.getText().isEmpty() || txtHoursLab.getText().isEmpty() || cmbLabSub.getSelectionModel().isEmpty())
    		return;

    	if(Integer.valueOf(txtHoursSub.getText()) >= Integer.valueOf(txtHoursLab.getText())) {
    		listSubjectChecked.setDisable(false);	
    		mapHSubject.put(txtIdSub.getText(), new Integer[] {Integer.valueOf(txtHoursSub.getText()), 
    					Integer.valueOf(txtHoursLab.getText()), 
    					Integer.valueOf(cmbLabSub.getSelectionModel().getSelectedIndex()) });
    	} else
    		listSubjectChecked.setDisable(true);			
    	
    }

    @FXML
    void viewSubject(MouseEvent event) {
    	if(listSubjectChecked.getItems().isEmpty())
    		return;
    	
    	Subject s = listSubjectChecked.getSelectionModel().getSelectedItem();
    	txtIdSub.setText(s.getSubjectID());
    	txtNameSub.setText(s.getName());
    	
    	if(mapHSubject.containsKey(s.getSubjectID())) {
    		txtHoursSub.setText(String.valueOf(mapHSubject.get(s.getSubjectID())[0]));
    		
    		if(mapHSubject.get(s.getSubjectID())[1] != 0) {
    			vbxLab.setDisable(false);
        		checkLab.setSelected(true);
    			txtHoursLab.setText(String.valueOf(mapHSubject.get(s.getSubjectID())[1]));
    			cmbLabSub.getSelectionModel().clearAndSelect(mapHSubject.get(s.getSubjectID())[2]);
    		} else {
    			vbxLab.setDisable(true);
        		checkLab.setSelected(false);
        		txtHoursLab.clear();
        		cmbLabSub.getSelectionModel().clearSelection();
    		}
    		
    	} else {
    		txtHoursSub.setText("0");
    		vbxLab.setDisable(true);
    		checkLab.setSelected(false);
    		txtHoursLab.clear();
    		cmbLabSub.getSelectionModel().clearSelection();
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listSubjectChecked != null : "fx:id=\"listSubjectChecked\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtGrade != null : "fx:id=\"txtGrade\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtCourseSub != null : "fx:id=\"txtCourseSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtHoursDisponible != null : "fx:id=\"txtHoursDisponible\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtHoursAssigned != null : "fx:id=\"txtHoursAssigned\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtIdSub != null : "fx:id=\"txtIdSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtNameSub != null : "fx:id=\"txtNameSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtHoursSub != null : "fx:id=\"txtHoursSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert checkLab != null : "fx:id=\"checkLab\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert vbxLab != null : "fx:id=\"vbxLab\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert txtHoursLab != null : "fx:id=\"txtHoursLab\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert cmbLabSub != null : "fx:id=\"cmbLabSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert hbxAllertSub != null : "fx:id=\"hbxAllertSub\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";
        assert btnAddNewCourse != null : "fx:id=\"btnAddNewCourse\" was not injected: check your FXML file 'CreateNewCourse.fxml'.";

    }
    
    public void setModel(Model model, String nameCourse, int grade, List<Subject> subjectList) {
		this.model = model ;
		this.mapHSubject = new HashMap<>();
		this.numHoursAssigned = 0;
		
		btnAddNewCourse.setDisable(true);
		
		// Materie
		listSubjectChecked.getItems().addAll(subjectList);
		cmbLabSub.getItems().addAll(model.getAllTypeLaib());
		
		txtCourseSub.setText(nameCourse);
		txtGrade.setText(String.valueOf(grade));
		txtHoursDisponible.setText(String.valueOf(model.getHoursWeekSchool()));
		txtHoursAssigned.setText(String.valueOf(0));
	}
}


