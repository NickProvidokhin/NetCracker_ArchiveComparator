package ru.ncedu.providokhin.archivecomparator;

public enum TypeOfChanged {
        NO_CHANGE,
        ADD,
        DELETE,
        CHANGE,
        RENAME;

        @Override
        public String toString() {
            switch (this) {
                case ADD:
                    return "+";
                case DELETE:
                    return "-";
                case CHANGE:
                    return "*";
                case RENAME:
                    return "?";
            }

            return "";
        }
}
