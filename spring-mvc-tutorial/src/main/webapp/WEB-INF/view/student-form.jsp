<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
    <title>Student Registration Form</title>
</head>

<body>

    <form:form action="processForm" modelAttribute="student">

    <br><br>

    First name: <form:input path="firstName" />

    <br><br>

    Last name: <form:input path="lastName" />

    <br><br>

    Country:

    <form:select path="country">

        <form:options items="${theCountryOptions}" />

    </form:select>

    <br><br>

    Favourite Language:

    <form:radiobuttons path="favouriteLanguage" items="${favouriteLanguageOptions}" />

    <br><br>


    Operating Systems:

    <form:checkbox path="operatingSystems" value="Linux" />
    <form:checkbox path="operatingSystems" value="Windows" />
    <form:checkbox path="operatingSystems" value="Mac" />

    <br><br>


    <input type="submit" value="Submit" />

    </form:form>

</body>

</html>