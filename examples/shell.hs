defmod Shellsort do 

  def int[] shellsort(valores : int[]) do
    
    int n = len(valores); # retorna tamanho do array valores, aqui nao eh comando para os tokens
    int h = 1;

    until(h < n) do
      h = h * 3 + 1;
    end

    h = h / 3;

    int c;
    int j;

    until(n > 2) do 
  
      rep (i : int = h, i < n, i = i + 1) do
        c = valores[i];
        j = i;

        until(j >= h && valores[j - h] > c) do
                valores[j] = valores[j - h];
                j = j - h;
        end

        valores[j] = c;
      end
    
      h = h / 2;      

    end

    return valores;
  end 

  def void main() do
  
    int[] valores;    

    until(read() != EOF) do 
      valores[] = lastValueRead();
    end
  
    int[] arr = shellsort(valores);
    
    rep(i : int = 0, i < len(valores), i = i + 1) do
      print(arr[i]);
      print("\n");
    end

  end

endmod
