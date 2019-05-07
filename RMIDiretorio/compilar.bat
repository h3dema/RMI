rem
rem #
rem #
rem # para compilar o conjunto no Windows
rem #
rem #

javac rmicomum/MgmtArquivosInterface.java
javac rmicomum/MgmtArquivos.java
rmic rmicomum.MgmtArquivos
javac rmicliente/Cliente.java
javac rmiservidor/Servidor.java
