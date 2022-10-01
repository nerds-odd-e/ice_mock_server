Feature: xml read write
  Rule: ch1 xxx

    Scenario: empty structure
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody/>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= null
        step= null
      }
      """

    Scenario: with empty param list
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <ParamList/>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= []
        step= null
      }
      """

    Scenario: with one string param
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <ParamList>
          <Param name="Step Name" dataObject="" type="S" maxLength="100" default="" />
        </ParamList>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= | name        | dataObject | maxLength | default_ | type  |
                   | 'Step Name' | ''         | 100       | ''       | : 'S' |
        step= null
      }
      """

    Scenario: with one integer param
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <ParamList>
          <Param name="Source DC Ramp" dataObject="" type="I" min="0" max="40000" descriptorList="" units="W/s" default="0" />
        </ParamList>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= | name             | dataObject | type  | min | max   | descriptorList | units | default_ |
                   | 'Source DC Ramp' | ''         | : 'I' | 0   | 40000 | ''             | 'W/s' | '0'      |
        step= null
      }
      """

    Scenario: with one double param
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <ParamList>
          <Param name="Pressure" dataObject="" type="D" min="0" max="100" units="mTorr" accuracy="0.01" default="10" />
        </ParamList>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= | name       | dataObject | type | min | max   | units   | accuracy | default_ |
                   | 'Pressure' | ''         | :'D' | 0.0 | 100.0 | 'mTorr' | 0.01     | 10.0     |
        step= null
      }
      """

    Scenario: with empty step list
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <Step index="1" name="1"/>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= null
        step= {
          index= 1
          name= '1'
          values= []
        }
      }
      """

    Scenario: with one step value
      Given the follow xml:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <Step index="1" name="1">
          <Value param="Step Name" value="StepName" />
          <Value param="Time" value="0" />
        </Step>
      </RecipeBody>
      """
      Then read the following recipe body and do the correct serialization
      """
      = {
        paramList= null
        step= {
          index= 1
          name= '1'
          values= | param       | value      |
                  | 'Step Name' | 'StepName' |
                  | 'Time'      | '0'        |
        }
      }
      """

    Scenario: acceptance test
      * the following recipe body read and write should pass:
      """
      <?xml version="1.0" encoding="UTF-8"?>

      <RecipeBody>
        <ParamList>
          <Param name="Step Name" dataObject="" type="S" maxLength="100" default="" />
          <Param name="Time" dataObject="" type="S" maxLength="256" default="" />
          <Param name="Pressure" dataObject="" type="D" min="0" max="100" units="mTorr" accuracy="0.01" default="10" />
          <Param name="Source DC" dataObject="" type="D" min="0" max="40000" units="W" accuracy="0.1" default="0" />
          <Param name="Source DC Ramp" dataObject="" type="I" min="0" max="40000" descriptorList="" units="W/s" default="0" />
          <Param name="Ar 1" dataObject="" type="D" min="-110" max="100" units="sccm" accuracy="0.01" default="0" />
          <Param name="N2" dataObject="" type="D" min="-110" max="200" units="sccm" accuracy="0.01" default="0" />
          <Param name="Gate Valve" dataObject="" type="I" min="0" max="2" descriptorList="Full:0,Mid:1,Close:2" units="" default="Full" />
          <Param name="Process Position" dataObject="" type="I" min="0" max="3" descriptorList="ProcessPos:0,PreDepPos:1,ProcessPos3:2,ProcessPos4:3" units="" default="ProcessPos" />
          <Param name="SpaceCompensation" dataObject="" type="I" min="0" max="1" descriptorList="Disable:0,Enable:1" units="" default="Disable" />
          <Param name="TimeCompensation" dataObject="" type="I" min="0" max="1" descriptorList="Disable:0,Enable:1" units="" default="Disable" />
          <Param name="PowerCompensation" dataObject="" type="I" min="0" max="1" descriptorList="Disable:0,Enable:1" units="" default="Disable" />
        </ParamList>
        <Step index="1" name="1">
          <Value param="Step Name" value="StepName" />
          <Value param="Time" value="0" />
          <Value param="Pressure" value="10" />
          <Value param="Source DC" value="0" />
          <Value param="Source DC Ramp" value="0" />
          <Value param="Ar 1" value="0" />
          <Value param="N2" value="0" />
          <Value param="Gate Valve" value="Full" />
          <Value param="Process Position" value="ProcessPos" />
          <Value param="SpaceCompensation" value="Disable" />
          <Value param="TimeCompensation" value="Disable" />
          <Value param="PowerCompensation" value="Disable" />
        </Step>
      </RecipeBody>
      """
