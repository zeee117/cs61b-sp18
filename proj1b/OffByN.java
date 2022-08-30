public class OffByN implements CharacterComparator{
    int num;
    public OffByN(int N){
        num = N;
    }

    public boolean equalChars(char x, char y){
        if(x-y == num || y-x == num){
            return true;
        }
        return false;
    }
}
