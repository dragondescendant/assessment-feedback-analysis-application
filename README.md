Assessment Feedback Analysis Application

University of Auckland
Software Engineering 788: Project Y
Semesters 1 and 2, 2019
Superviser: Dr Nasser Giacaman
Student: Gen Li

This application is part of a project that the repository owner (Gen Li) had to complete for the course Software Engineering 788: Project Y under the supervision of Dr Nasser Giacaman at the University of Auckland during semesters 1 and 2 of 2019 towards the university's Master of Engineering Studies in Software Engineering.

It's for analysing data from feedback that's given to university students for assessments by peers and teachers. The implementation is based on feedback that's collected using qualtrics surveys (https://www.qualtrics.com) that consist of an arbitrary number of questions for which the answer(s) can either be textual or one among interval-based measurements.

The primary functionalities of the system are:
- The inputting of comma separated value files that are converted from surveys of the said type.
- The processing and storage of data from comma separated value files that are inputted.
- The retrieval of stored data.
- The manipulation and visualisation of retrieved data for analysis.

The application consists of a REST server that's implemented using Java and Spring WebFlux and an interface that's implemented using React.
