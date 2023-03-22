fun solution(setSource: Set<String>, listSource: MutableList<String>): MutableSet<String> {
   return mutableSetOf<String>().plus(setSource).toMutableSet().plus(listSource).toMutableSet()
}