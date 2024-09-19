# FakeTDL Data Format Specification 

Version 1.0

## Introduction

FakeTDL (v1.0) is a fictional data format intended to be used to illustrate the data format and
cybersecurity data filtering issues
that arise in military [Tactical Data Link (TDL)](https://en.wikipedia.org/wiki/Tactical_data_link)
data.

## FakeTDL Messages

FakeTDL has only a few kinds of messages:

- Track - an observed or estimated point for a moving entity
- Identity - information about the entity - friend/foe, air, land, sea, weapon, etc.
- Ack - acknowledges receipt of one or more messages that require acknowledgement

## Common Concepts

Every message identifies the sender of that message and send time.
This allows concepts of "most recent message received" and 
"most recent observation" to be distinguished as needed to 
illustrate filtering scenarios.

### Unit and Track Numbers

Every participating entity is identified by a _Unit Number_. For an entity that is
reporting an observation about another entity, this is the _source_ unit
number, and the observed entity has a _track number_. 

Unit and Track numbers are 5 character strings. The first two characters are
alphanumeric, allowing only A-Z and 0-9. The last 3 characters
are 0-9 only.

Filtering logic will often need to perform distinct filtering based on
source unit numbers and track numbers. 

Real TDLs have a number of complexities that are omitted from FakeTDL
in the interest of keeping it simpler. For example real TDLs have a
way to eliminate duplicate track numbers that occur when more than one
participant creates track observations about what turns out to be the same
tracked entity. 
Fake TDL (version 1.0) does not contain track-number management messaging. 
Hence, scenarios must assume track
numbers are enduring and don't need to be revised/replaced.
(Future versions of FakeTDL may revisit this, but we currently believe
there is no loss of generality from omitting this capability.)

### Geolocations
Geolocations are captured as lat/lon and elevation. The source
(observer) location is typically reported along with the observed entity
location, so that the distinction between these (and possibly even distance 
between them) can contribute to illustrations of filtering decisions. 

Latitude values are in degrees from -90.0 to 90.0, and 
longitude values are -180.0 to 180.0. Both are stored as 32-bit IEEE float numbers.

Elevation values are from -1000 to 20000, the units of measure are 25 feet
above/below Mean Sea Level (MSL). Elevations are stored as 2-byte signed binary integers.

### Time Fields

Times of observations or message send/receive times are measured in hours, minutes, seconds 
since midnight UTC (also commonly called Zulu time). Times are stored in 3 bytes in 
Binary-Coded Decimal (BCD) representation. Hence the time 13:24:47Z is represented as the bytes 
0x13, 0x24, 0x47.   

## FakeTDL Message Details

FakeTDL data is big-endian. The data fields are a mixture of binary and text. 
Text characters are always 8-bit ASCII so 1 character occupies 1 byte. 

Messages are all 64 bytes long. Unused data bytes at the end of a message
are filled with 0x53 (which corresponds to ASCII 'X' if you look at raw data as text.)

All messages start with these fields:

- messageType - 1 character (T, D, or A for Track, IDentity, and Ack)
- source unit number - originator of the message (5 characters)
- message send time - time of day. Note that 
  message send time is distinguished from the time an observation was made. 

Other fields depend on the message type.

### Track Message

Track messages each carry information about 1 point on a track. This point can be the start or end
of the track, or a waypoint on the track in between. The information can be marked as estimated
or actual/observed.

##### Track Message Fields 
- ack required (whether an ack is required or not) - 1 byte boolean. 0 is false, 1 is true.
- messageID (must be sent back on ACK messages) 4 byte unsigned integer.
  The value zero means "no statement" meaning there is no messageID. The maximum value is 99999.
- source (aka observer) lat/lon 
- source elevation
- track number (of observed entity) 
- observation time 
- observed lat/lon and elevation 
- start/end/waypoint - specifies what kind of track point (1 character, S, E, W)
- estimated or actual - 1 character (E or A)
- course - 2 byte unsigned integer from 0 to 359 degrees.
- speed - 2 byte unsigned integer from 0 to 2000 data-miles per hour. 
(A data-mile is 6000 feet)

##### About Inter-Message Track Filtering

A principal goal for FakeTDL has been to enable creation of data for 
data filtering scenarios where state must be saved and messages cannot be processed until other 
related messages are received. 

FakeTDL track messages are able to illustrate _inter-message correlation_. 
For example, a track way-point message may need to be routed based on 
the geolocation of the start point of the track and whether that start point is inside/outside 
of certain geo-region boundaries; however, the start point location is carried by 
a separate track message (sharing the same track number).

### Identity Message
Identity messages carry the more slowly changing, or non-changing
information about a track or unit.
Most importantly, it contains IFF (Identity Friend or Foe) status, but
also descriptive information about the type of an entity.
Data filtering rules often redact or block some kinds of identity
information.

##### Identity Message Fields
- ack required (whether an ack is required or not) - 1 byte boolean. 0 is false, 1 is true.
- messageID (must be sent back on ACK messages) 4 byte unsigned integer.
  The value zero means "no statement" meaning there is no messageID. The maximum value is 99999.
- track number (of the entity being described by this message)
- observation time
- IFF - Identity Friend or Foe - indicates Friend, Enemy, or Unknown (1 character, F, E, or U)
- entity category (Land, Sea, Sub, Air, Weapon) (1 character, L, S, U, A, or W)
- type - numeric identifier of specific entity (2 byte unsigned integer) 0 means type unspecified.
- description (text) - up to 38 characters of text restricted to:
  - Upper case letters, digits, spaces, parentheses, square brackets
  - These punctuation characters ".-_/:" (without the quotes).
  - Spaces and punctuation characters must have 
    non-punctuation characters surrounding them.
  - The text must begin with an alpha-numeric character
  - The text must end with an alpha-numeric character, closing parenthesis, or closing square 
    bracket. 
  - Control characters like line endings and TABs are not allowed. 
  - Trailing unused characters (of the available length) are filled with NUL (character code 0).



### Ack Message
- dest unit number (5 characters)
- messageID (being acknowledged) (repeats up to 12 times) (each is a 4 byte unsigned integer)

One ack message can acknowledge the receipt of multiple track/identity 
messages that requested acknowledgement. That is, fakeTDL can be used
to illustrate scenarios where acks are sent infrequently relative to
track and ident messages.

FakeTDL contains ack messages because TDLs generally require that 
some messages are acknowledged. For example, when 
an Identity message changes the IFF of an entity from Enemy to Friend,
then an ACK must be sent back to the source of that update.

This required ack feature in fakeTDL allows it to be used to 
illustrate the critical
need for bidirectional communications in TDL systems.
