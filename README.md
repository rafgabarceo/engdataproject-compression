# ENGDATA PROJECT - COMPRESSION EXPERIMENT

Dependencies:
- JVM-Brotli
- Apache Commons Compress
- XZ Java Compression (dependency for the Apache Commons Compress)

The program is a terminal-based application. Invoke the application the program, and after the invocation, add the files
as arguments.

`java -jar {jarname} file.txt file2.txt file3.txt ... fileN.txt`

The program will output two files, a compressed file and a decompressed file. 

## Contributing
The program utilizes Maven. When pulling the repository, it is recommended to utilize IntelliJ. Along with this, be sure to download all dependencies. 

## Packaging 
The project utilizes Maven Shade to package all the needed dependencies into one .jar. The default Maven `package` process does not have this feature. Therefore, when exporting, there will be two .jar files. Grab the one without the `original` in its name.
