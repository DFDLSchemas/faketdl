# FakeTDL - a Simple Quasi-Tactical Data Link Format

FakeTDL (v1.0) is a fictional data format intended to be used to illustrate the data format and
cybersecurity data filtering issues
that arise in military [Tactical Data Link (TDL)](https://en.wikipedia.org/wiki/Tactical_data_link)
data.

The specifications of real TDL data formats are all non-public, as are the specifications of the 
data transformation and routing rules. 
FakeTDL defines TDL-like messages that allow illustration of the _kinds_ of 
data format and data transformation that are required for TDL data, without the need to disclose 
any non-public specifications.

FakeTDL facilitates training and study of:

- [Data Format Description
Language (DFDL)](https://en.wikipedia.org/wiki/Data_Format_Description_Language) descriptions of TDL data formats

- Schematron rules for advanced validation of TDL data
- XSLT to block specific kinds of messages
- XSLT to redact (overwrite), modify numeric precision, or remove optional fields

- Basic Geo-location filtering (removing messages that are about
  entities outside the area of interest, or modifying locations of
  messages (or blocking messages) that are within exclusion regions)

- Advanced Filtering Scenarios including Inter-Message Correlation and 
Inter-Source Corroboration

The FakeTDL messages and fields are described in the 
[FakeTDL Specification](FakeTDLSpecification.md).
