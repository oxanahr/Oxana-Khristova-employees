# Oxana-Khristova-employees
Application that identifies the pair of employees who have worked together on common projects for the longest period of time.

# Input data:

A CSV file with data in the following format:

EmpID, ProjectID, DateFrom, DateTo

DateTo can be NULL, equivalent to today

# Sample data:

143, 12, 2013-11-01, 2014-01-05

218, 10, 2012-05-16, NULL

143, 10, 2009-01-01, 2011-04-27

...

The user picks up a file from the file system and, after selecting it, all common projects of the pair are displayed in datagrid 

with the following columns: Employee ID #1, Employee ID #2, Project ID, Days worked 

# Sample output:

143, 218, 8, 457
