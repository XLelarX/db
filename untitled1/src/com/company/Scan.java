package com.company;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

class Scan {
    private static int NAME_LENGTH = 31;//ToDo может ли больше?

    private final static int lexName = 0, lexAbstract = 1, lexNum = 2, lexAssert = 3,
            lexBoolean = 4, lexBreak = 5, lexByte = 6, lexCase = 7,
            lexCatch = 8, lexChar = 9, lexClass = 10, lexConst = 11, lexContinue = 12,
            lexDefault = 13, lexDo = 14, lexMult = 15, lexPlus = 18, lexMinus = 19, lexSemi = 29, lexAss = 30, lexLPar = 31,
            lexRPar = 32, lexEOT = 33, lexDouble = 34, lexElse = 35, lexEnum = 36,
            lexExtends = 37, lexFinal = 38, lexFinally = 39, lexFloat = 40, lexFor = 41,
            lexGoTo = 42, lexIf = 43, lexImplements = 44, lexImport = 45, lexInstanceOf = 46,
            lexInt = 47, lexInterface = 48, lexLong = 49, lexNative = 50, lexNew = 51,
            lexPackage = 52, lexPrivate = 53, lexProtected = 54, lexPublic = 55, lexReturn = 56,
            lexShort = 57, lexStatic = 58, lexStrictFP = 59, lexSuper = 60, lexSwitch = 61,
            lexSynchronized = 62, lexThis = 63, lexThrow = 64, lexThrows = 65, lexTransient = 66,
            lexTry = 67, lexVoid = 68, lexVolatile = 69, lexWhile = 70, lexDiv = 73, lexNull = 106, lexTrue = 107, lexFalse = 108;


    static int Lex;

    private static StringBuffer Buf = new StringBuffer(NAME_LENGTH);

    private static String Name;

    private static int KWNUM = 53;
    private static int nkw = 0;

    private static class Item {
        private String Word;
        private int Lex;
    }

    private static Item[] KWTable = new Item[KWNUM];

    private static void EnterKW(String Name, int Lex) {
        (KWTable[nkw] = new Item()).Word = Name;
        KWTable[nkw++].Lex = Lex;
    }

    private static int TestKW() {
        for (int i = nkw - 1; i >= 0; i--)
            if (KWTable[i].Word.compareTo(Name) == 0)
                return KWTable[i].Lex;
        //TODO Не повторяющиеся имена?
        return lexName;
    }

    private static void Ident() {
        int i = 0;
        Buf.setLength(0);

        do {
            if (i++ < NAME_LENGTH)
                Buf.append((char) Text.ch);
            else
                Error.Message("Слишком длинное имя");
            System.out.print((char) Text.ch);
            Text.NextCh();
        } while (Character.isLetterOrDigit((char) Text.ch));
        //} while (Character.isLetterOrDigit((char) Text.ch) || Text.ch == '_' || Text.ch == '$');

        Name = Buf.toString();
        Lex = TestKW();
        System.out.println("    Идентификатор   " + Lex);
    }

    private static void Number() {
        Lex = lexNum;

        while (Text.ch == 'I' || Text.ch == 'V' || Text.ch == 'X' || Text.ch == 'L' || Text.ch == 'C' || Text.ch == 'D' || Text.ch == 'M') {
            System.out.print((char) Text.ch);
            Text.NextCh();
        }
        System.out.println("    Число   " + Lex);
    }

    static void nextLex() {
        while (Text.ch == Text.chSpace || Text.ch == Text.chEOL || Text.ch == Text.chTab)
            Text.NextCh();

        Location.LexPos = Location.Pos;

        //if (Character.isLetter((char) Text.ch) || Text.ch == '_' || Text.ch == '$')
        if (Text.ch == '$')
            Ident();

        else if (Text.ch == 'I' || Text.ch == 'V' || Text.ch == 'X' || Text.ch == 'L' || Text.ch == 'C' || Text.ch == 'D' || Text.ch == 'M')
            Number();

        else {

            switch (Text.ch) {
                case ';':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexSemi;
                    System.out.println("    Точка с запятой   " + Lex);
                    break;

                case ':':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    if (Text.ch == '=') {
                        System.out.print((char) Text.ch);
                        Text.NextCh();
                        Lex = lexAss;
                        System.out.println("    Знак присваивания   " + Lex);
                    } else Error.Expected("Недопустимый символ");
                    break;

                case '(':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexLPar;
                    System.out.println("    Левая скобка   " + Lex);
                    break;

                case ')':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexRPar;
                    System.out.println("    Правая скобка   " + Lex);
                    break;

                case '+':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexPlus;
                    System.out.println("    Плюс   " + Lex);
                    break;

                case '-':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexMinus;
                    System.out.println("    Минус  " + Lex);
                    break;

                case '*':
                    System.out.print((char) Text.ch);
                    Text.NextCh();
                    Lex = lexMult;
                    System.out.println("    Умножение   " + Lex);
                    break;

                case '/':
                    Text.NextCh();
                    if (Text.ch != '/') {
                        System.out.print('/');
                        Lex = lexDiv;
                        System.out.println("    Деление   " + Lex);
                    } else {
                        lineComment();
                        nextLex();
                    }
                    break;

                case Text.chEOT:
                    Lex = lexEOT;
                    break;

                default:
                    Error.Message("Недопустимый символ");
            }
        }
    }

    private static void lineComment() {
        Text.NextCh();

        while (Text.ch != Text.chEOT && Text.ch != Text.chEOL)
            Text.NextCh();
    }

//    private static void slashCombinations() {
//        if (Text.ch == 'u')
//            numberOfUnicode();
//        else if (isOctNumber())
//            numberOfLatin();
//        else if (Text.ch == 'b') {
//            Text.NextChForChar();
//            Lex = lexBS;
//        } else if (Text.ch == 't') {
//            Text.NextChForChar();
//            Lex = lexHT;
//        } else if (Text.ch == 'n') {
//            Text.NextChForChar();
//            Lex = lexLF;
//        } else if (Text.ch == 'f') {
//            Text.NextChForChar();
//            Lex = lexFF;
//        } else if (Text.ch == 'r') {
//            Text.NextChForChar();
//            Lex = lexCR;
//        } else if (Text.ch == '\"') {
//            Text.NextChForChar();
//            Lex = lexDoubleQuote;
//        } else if (Text.ch == '\'') {
//            Text.NextChForChar();
//            Lex = lexQuote;
//        } else if (Text.ch == '\\') {
//            Text.NextChForChar();
//            Lex = lexBackSlash;
//        } else {
//            Location.LexPos = Location.Pos;
//            Error.Message("Недопустимый символ");
//        }
//    }

//    private static void numberOfLatin() {
//        String checkString = "" + (char) Text.ch;
//        Text.NextCh();
//
//        for (int i = 0; i < 2; i++) {
//            if (isOctNumber()) {
//                // System.out.println("     :" + (char) Text.ch);
//                checkString += (char) Text.ch;
//                if (Integer.parseInt(checkString) <= 377) {
//                    Text.NextCh();
//                } else {
//                    Location.LexPos = Location.Pos - 2;
//                    Error.Message("Слишком большое число");
//                }
//            } else
//                return;
//        }
//
//        Lex = lexLatin;
//    }

//    private static boolean isBinNumber() {
//        return Text.ch >= '0' && Text.ch <= '1';
//    }
//
//    private static boolean isOctNumber() {
//        return Text.ch >= '0' && Text.ch <= '7';
//    }
//
//    private static boolean isHexNumber() {
//        return Character.isDigit((char) Text.ch) || (Text.ch >= 'B' && Text.ch <= 'F') || (Text.ch >= 'a' && Text.ch <= 'f');
//    }
//
//    private static void numberOfUnicode() {
//        while (Text.ch == 'u')
//            Text.NextChForChar();
//
//        String checkString = "\\u";
//
//        for (int i = 0; i < 4; i++) {
//            checkString += (char) Text.ch;
//            if (Objects.equals(checkString, "\\u000a") || Objects.equals(checkString, "\\u000d") || Objects.equals(checkString, "\\u000A") || Objects.equals(checkString, "\\u000D")) {
//                Location.LexPos = Location.Pos;
//                Error.Message("LineTerminator");
//            } else if (Objects.equals(checkString, "\\u005c") || Objects.equals(checkString, "\\u005C")) {
//                Text.NextCh();
//                slashCombinationsWithoutUnicode();
//            } else if (isHexNumber())
//                Text.NextChForChar();
//            else {
//                Location.LexPos = Location.Pos + 1;
//                Error.Message("Недопустимый символ");
//            }
//        }
//        Lex = lexUnicode;
//    }
//
//    private static void slashCombinationsWithoutUnicode() {
//        if (isOctNumber())
//            numberOfLatin();
//        else if (Text.ch == 'b') {
//            Text.NextCh();
//            Lex = lexBS;
//        } else if (Text.ch == 't') {
//            Text.NextCh();
//            Lex = lexHT;
//        } else if (Text.ch == 'n') {
//            Text.NextCh();
//            Lex = lexLF;
//        } else if (Text.ch == 'f') {
//            Text.NextCh();
//            Lex = lexFF;
//        } else if (Text.ch == 'r') {
//            Text.NextCh();
//            Lex = lexCR;
//        } else if (Text.ch == '\"') {
//            Text.NextCh();
//            Lex = lexDoubleQuote;
//        } else if (Text.ch == '\'') {
//            Text.NextCh();
//            Lex = lexQuote;
//        } else if (Text.ch == '\\') {
//            Text.NextCh();
//            Lex = lexBackSlash;
//        } else {
//            Location.LexPos = Location.Pos;
//            Error.Message("Недопустимый символ");
//        }
//    }

    static void init() {
//        nkw = 0;
//
//        EnterKW("abstract", lexAbstract);
//        EnterKW("assert", lexAssert);
//        EnterKW("boolean", lexBoolean);
//        EnterKW("break", lexBreak);
//        EnterKW("byte", lexByte);
//        EnterKW("case", lexCase);
//        EnterKW("catch", lexCatch);
//        EnterKW("char", lexChar);
//        EnterKW("class", lexClass);
//        EnterKW("const", lexConst);
//        EnterKW("continue", lexContinue);
//        EnterKW("default", lexDefault);
//        EnterKW("do", lexDo);
//        EnterKW("double", lexDouble);
//        EnterKW("else", lexElse);
//        EnterKW("enum", lexEnum);
//        EnterKW("extends", lexExtends);
//        EnterKW("final", lexFinal);
//        EnterKW("finally", lexFinally);
//        EnterKW("float", lexFloat);
//        EnterKW("for", lexFor);
//        EnterKW("goto", lexGoTo);
//        EnterKW("if", lexIf);
//        EnterKW("implements", lexImplements);
//        EnterKW("import", lexImport);
//        EnterKW("instanceof", lexInstanceOf);
//        EnterKW("int", lexInt);
//        EnterKW("interface", lexInterface);
//        EnterKW("long", lexLong);
//        EnterKW("native", lexNative);
//        EnterKW("new", lexNew);
//        EnterKW("package", lexPackage);
//        EnterKW("private", lexPrivate);
//        EnterKW("protected", lexProtected);
//        EnterKW("public", lexPublic);
//        EnterKW("return", lexReturn);
//        EnterKW("short", lexShort);
//        EnterKW("static", lexStatic);
//        EnterKW("strictfp", lexStrictFP);
//        EnterKW("super", lexSuper);
//        EnterKW("switch", lexSwitch);
//        EnterKW("synchronized", lexSynchronized);
//        EnterKW("this", lexThis);
//        EnterKW("throw", lexThrow);
//        EnterKW("throws", lexThrows);
//        EnterKW("transient", lexTransient);
//        EnterKW("try", lexTry);
//        EnterKW("void", lexVoid);
//        EnterKW("volatile", lexVolatile);
//        EnterKW("while", lexWhile);
//        EnterKW("null", lexNull);
//        EnterKW("true", lexTrue);
//        EnterKW("false", lexFalse);


        nextLex();
    }
}