OODBMS - db4o

Reasons for choice -
1. Alternative oodbms were unacceptable:
  1. zooDB
    - an api exists for any data that is going to be persistent
    - immature code base, still in development
  2. Perst
    - an api exists for any data that is going to be persistent
    - in an attempt to stay viable for use in older embedded systems, it does not support newer technologies
    - needs to register for the forum to access the source-code
  3. Neodatis ODB
  4. FramerD
    - no examples or guide available
  5. Zope Object Virtuoso
    - implementation in non oo-language (python)
  6. Wakanda
    - implementation in non oo-language (javascript)
    - probably just a document store in disguise
2. Specs of db4o
  - db4o is the least invasive of the tested programs, they allow for our data code to be written without much change
  - db4o is first and foremost an "in-memory" database, as much as possible is stored in memory
  - things are saved to disk when unused or when instructed

Link to its website:
  - http://supportservices.actian.com/versant/default.html (working)
  - http://www.db4o.com/ (inactive)
  - https://github.com/pegurnee/db4o.git (source code backup)
