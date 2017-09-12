package common;

/**
 *
 * @author ezequiel
 */
public enum Terminal {
    ABRE_PARENTESIS,
    ASIGNACION,
    CIERRA_PARENTESIS,
    MAS,
    MENOS,
    POR,
    DIVIDIDO,
    MAYOR,
    MENOR,
    IGUAL,
    MAYOR_IGUAL,
    MENOR_IGUAL,
    DISTINTO,
    ODD,
    IDENT,
    NUMERO,
    CADENA_TEXTO,
    NULO,
    SALTO_LINEA,
    PUNTO_COMA,
    CALL,
    COMA,
    BEGIN,
    END,
    PROCEDURE,
    VAR,
    CONST,
    IF,
    THEN,
    WHILE,
    READLN,
    WRITELN,
    WRITE,
    EOF,

    /**
     *Reservadopara que no haya ciclos
     */
    CERRADO, //Reservador para que no haya ciclos
    
    /**
     * Salto a un bloque
     */
    BLOQUE, EXPRESION, PROPOSICION;
}

