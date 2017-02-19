def pascal(c: Int, r: Int): Int = {
  if (c == 0 || c == r) 1
  else pascal(c-1, r-1) + pascal(c, r-1)
}


pascal(0, 1)
pascal(0, 1)
pascal(4, 5)
pascal(1, 2)
pascal(1, 3)
pascal(2, 4)
pascal(3, 5)
pascal(2, 5)


def balance(chars: List[Char]): Boolean = {
  def isBalanced(chars: List[Char], count: Int): Boolean = {
    if (chars.isEmpty) count == 0
    else {
      if (chars.head == '(') isBalanced(chars.tail, count + 1)
      else if (chars.head == ')' && count == 0) false
      else if (chars.head == ')') isBalanced(chars.tail, count - 1)
      else isBalanced(chars.tail, count + 0)
    }
  }
  if (chars.isEmpty) true
  else isBalanced(chars, 0)
}

balance("".toList)
balance("()".toList)
balance("(if (zero? x) max (/ 1 x))".toList)
balance("I told him (that it’s not (yet) done). (But he wasn’t listening)".toList)

balance("((()".toList)
balance(":-)".toList)
balance("())(".toList)

def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0) 0
    else if (coins.isEmpty)
      if (money == 0) 1 else 0
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
}

countChange(4,List(1,2)) //3
countChange(300,List(5,10,20,50,100,200,500)) // 1022
countChange(301,List(5,10,20,50,100,200,500)) // 0
countChange(300,List(500,5,50,100,20,200,10)) // 1022

