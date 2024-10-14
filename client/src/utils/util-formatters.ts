export function cellStyle(params){
    if(params.value > 0){
        return {color: '#7ACC79'}
    }else if (params.value < 0){
        return {color: '#F9644D'}
    } else {
        return {color: '#FFFFFF'}
    }
};