package co.simplon.alt3.kisslulerback.library.exception;

public class WrongPasswordException extends Exception {

  public WrongPasswordException() {
    super("passwod didn't match");
  }

}
