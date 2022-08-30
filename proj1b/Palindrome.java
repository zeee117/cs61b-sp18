public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> a = new ArrayDeque();

        for(int i = 0; i < word.length(); i++){
            a.addLast(word.charAt(i));
        }
        return a;
    }

    private boolean helperIsPalindrome(Deque<Character> deque){
        if(deque.size() <= 1){
            return true;
        }
        char f = deque.removeFirst();
        char l = deque.removeLast();
        if(f != l){
            return false;
        }
        return helperIsPalindrome(deque);
    }

    public boolean isPalindrome(String word){
        Deque<Character> deque = wordToDeque(word);
        return helperIsPalindrome(deque);
    }

    /* Overload isPalindrome(String word) */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);

        while(deque.size() > 1){
            char f = deque.removeFirst();
            char l = deque.removeLast();
            if(!cc.equalChars(f, l)){
                return false;
            }
        }
        return true;
    }
}
