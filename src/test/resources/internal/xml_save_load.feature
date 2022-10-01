Feature: xml read write
  Rule: ch1 xxx

    Scenario: empty structure
      * read and load ch:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody/>
      """

    Scenario: with empty param list
      * read and load ch:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <RecipeBody>
        <ParamList/>
      </RecipeBody>
      """
