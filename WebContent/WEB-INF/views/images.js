var imgArray = new Array();

imgArray[0] = new Image();
imgArray[0].src = './Figures/money.jpg';

imgArray[1] = new Image();
imgArray[1].src = './Figures/tarjeta.jpg';

imgArray[2] = new Image();
imgArray[2].src = './Figures/yourself.jpg';


/*------------------------------------*/

function nextImage(element)
{
    var img = document.getElementById(element);

    for(var i = 0;i<imgArray.length;i++)
    {
        if(imgArray[i] == img)
        {
            if(i == imgArray.length)
            {
                var j = 0;
                document.getElementById(element).src = imgArray[j].src;
                break;
            }
            else
            var j = i + 1;
            document.getElementById(element).src = imgArray[j].src;
            break;
        }
    }   
}
