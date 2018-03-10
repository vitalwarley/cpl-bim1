defmod Fibonacci do
  def void fib(limite : int) do
    int[limite+1] arr;
    arr[0] = 0;
    arr[1] = 1;
    int i = 0;

	when(limite <= 0) do
    	print("0");
    end

    until(i < limite) do
      when (i > 2) do
        arr[i] = arr[i-1] + arr[i-2];
      end
      
      print(arr[i]);
      
      when(i != limite-1) do
        print(", ");
      end
      
      i = i + 1;
    end
  end


  def void main() do
    int limite = read();
    fib(limite);
  end

endmod
