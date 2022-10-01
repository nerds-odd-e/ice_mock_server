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
        }
      }
      """
