import AWS from 'aws-sdk'

const S3_BUCKET = 's3-victor-lab-bandtec';
const REGION = 'us-east-1';

AWS.config.update({
    accessKeyId: 'ASIAR2HN4XAAXNZQQEN3',
    secretAccessKey: 'zt5WiRLp0jvdBGKYrAUew2n5wneUdnbgiQk2p6xq',
    sessionToken: 'FwoGZXIvYXdzEKr//////////wEaDOWzVvaSph25y6cMsyLNAfkkAUz/cGJDdHDJ/X4/hlfK9Em4gBeSsN+/Xf+YML3dhgLgpDODzo0cmyAael3g2zd3l82I+OvSy2cVUgHepFCF/2YGaM7Ux5mz4pwSIH4mvaCPSLjsD9BdhfEpI/OKGNiS5scaOGv4/VwCp4ctuwm/kBS1Zs6L6W7eGifwvvF/vPXpMJDHP59KhwWr8cGP2i9erv0l1ROOnr7nZRYtrDIKSVwV1kPPrIQb8yaty2AwnjefvCvgujtNSZumhrl8kQChXWbTdqN3stUuO6soscqYiwYyLTJ+ExZnhVs5DN30ikiOE7AvHpv0E43vhbrHIGbxIzktUhjxhw6ALShq/4/7AQ=='
})

const bucketFinoban = new AWS.S3({
    params: {
        Bucket: S3_BUCKET
    },
    region: REGION,
})

const GravarArquivoS3 = (arquivo) => {
    const params = {
        ACL: 'public-read',
        Body: arquivo,
        Bucket: S3_BUCKET,
        Key: arquivo.type,
        ContentType: 'application/pdf'
    };

    bucketFinoban.putObject(params)
        .send((err) => {
            window.open('https://s3-victor-lab-bandtec.s3.amazonaws.com/application/pdf', '_blank');
        });
}

export default GravarArquivoS3;