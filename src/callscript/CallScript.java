
package callscript;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFormattedTextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CallScript extends JFrame implements ActionListener {

     JTextArea scriptArea;                  // Displays call script
     JButton continueButton;                // Saves changes and loads new script
     JTextArea enterInfo;                   // Allows user to enter information
     JComboBox selectResponse;              // Menu of consumer responses
     String response;                       // Response selected from combobox
     String script;                        // Message to be displayed in call script area
     
    CallScript(){
    GridBagConstraints layoutConst = null; // Used to specify GUI component layout
      
      JLabel scriptLabel = null;              // Label for call script area
      JLabel responseLabel = null;            // Label for response combobox
      JLabel infoLabel = null;                // Label entering information
      response = "no response selected";
      script = "Thank you for calling [Company Name]. My name is [Agent Name]. \n "
              + "May I ask with whom I am speaking?\n" +
             "Thanks, [Consumer Name]. In order to verify that I am speaking to the correct party, \n"
              + " please verify your full mailing address. \n "
              + "(Check box for ‘Address Verified’ if verified)\n" +
            "Thank you. Will you please verify your [DOB/last 4 of SSN/etc.]? \n " 
              + "(Check box for ‘[Selected Info] Verified’ if verified)";
      

      

      // Set frame's title
      setTitle("Incoming Call");

      // Create labels
      scriptLabel = new JLabel("Script");
      responseLabel = new JLabel("Select Response");
      infoLabel = new JLabel("Enter Information");

      // Create call script area make it not editable
      scriptArea= new JTextArea(20, 50); 
      scriptArea.setEditable(false);
      
      //Create customer info area and make it editable
      enterInfo = new JTextArea(6, 10);
      enterInfo.setEditable(true);

      //Create continue button
      continueButton = new JButton("Start");
      continueButton.addActionListener(this);

      // Create response options combobox
      String[] options = {"--", "verified", "refused to verify", "wrong party"}; 
      selectResponse = new JComboBox(options);
      selectResponse.addActionListener(this);
      

      // Use a GridBagLayout
      setLayout(new GridBagLayout());
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 1;
      layoutConst.gridy = 0;
      add(scriptLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 1;
      layoutConst.gridy = 1;
      add(scriptArea, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 0;
      layoutConst.gridy = 2;
      add(responseLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 0;
      layoutConst.gridy = 3;
      add(selectResponse, layoutConst);
     
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 2;
      layoutConst.gridy = 2;
      add(infoLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 2;
      layoutConst.gridy = 3;
      add(enterInfo, layoutConst);
   
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(0,0,0,0);
      layoutConst.gridx = 1;
      layoutConst.gridy = 4;
      add(continueButton, layoutConst);
    
    }
     
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Object obj = ae.getSource();
        if (obj == selectResponse){
             JComboBox cb = (JComboBox)ae.getSource();
             response = (String)cb.getSelectedItem();
             continueButton.setText("Continue");
        }
        if (obj == continueButton){
          scriptArea.setText("");
          changeScript(response);
          scriptArea.append(script);          
        }         
    }
    
    String changeScript(String theResponse){
        if (theResponse.equals("verified")){
                 script = "Thank you for verifying that for me. Before we continue any further, federal law requires me to\n" +
                "inform you that this is an attempt to collect a debt by a debt collector and that any information\n" +
                "obtained will be used for that purpose. Additionally, calls may be monitored or recorded for\n" +
                "training and audit purposes. Do we have your permission to record this call?";
                 return script;
             }
        if (theResponse.equals("refused to verify")){
                script = "[Consumer Name], unfortunately, I am unable to proceed any further with the call if you do not\n" +
                "complete the verification step. This is required in order to ensure that your confidential\n" +
                "information is not shared with unauthorized third parties. Do you wish to proceed with the call?";
                return script;
        }
        if (theResponse.equals("wrong party")){
                script = "It appears that you are not the party we are trying to reach. I will remove your phone number\n" +
                "and/or address from our database to prevent any unnecessary communications in the future.\n" +
                "Thank you for your patience and have a good day.";
                return script;
        }
        if(theResponse.equals("--")){
                script = "Error: no response selected";
                return script;
        }
        return "script error";
                
    }
    
    public static void main(String[] args) {
      CallScript myFrame = new CallScript();
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.pack();
      myFrame.setVisible(true);
    }
    
}
